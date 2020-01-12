import botometer
from tweepy import API
from tweepy import Cursor
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import json
import twitter_credentials

BOTSCORE = 0.0001
LIST1 = ["#الجهاد_الاسلامي", "#رجال_الانفاق", "#Farrakhan", "حب"]
LIST = ["Israel gay"]
TWEETS_FILENAME = "tweets2.txt"


# # # # TWITTER CLIENT # # # #
class TwitterClient():
    def __init__(self, twitter_user=None):
        self.auth = TwitterAuthenticator().authenticate_twitter_app()
        self.twitter_client = API(self.auth)
        self.twitter_user = twitter_user

    def get_user_timeline_tweets(self, num_tweets):
        tweets = []
        for tweet in Cursor(self.twitter_client.user_timeline, id=self.twitter_user).items(num_tweets):
            tweets.append(tweet)
        return tweets

    def get_friend_list(self, num_friends):
        friend_list = []
        for friend in Cursor(self.twitter_client.friends, id=self.twitter_user).items(num_friends):
            friend_list.append(friend)
        return friend_list

    def get_home_timeline_tweets(self, num_tweets):
        home_timeline_tweets = []
        for tweet in Cursor(self.twitter_client.home_timeline, id=self.twitter_user).items(num_tweets):
            home_timeline_tweets.append(tweet)
        return home_timeline_tweets


# # # # TWITTER AUTHENTICATER # # # #
class TwitterAuthenticator():
    def authenticate_twitter_app(self):
        auth = OAuthHandler(twitter_credentials.CONSUMER_KEY, twitter_credentials.CONSUMER_SECRET)
        auth.set_access_token(twitter_credentials.ACCESS_TOKEN, twitter_credentials.ACCESS_TOKEN_SECRET)
        return auth


# # # # TWITTER STREAMER # # # #
class TwitterStreamer():
    """
    Class for streaming and processing live tweets.
    """

    def __init__(self):
        self.twitter_autenticator = TwitterAuthenticator()

    def stream_tweets(self, fetched_tweets_filename, hash_tag_list):
        # This handles Twitter authetification and the connection to Twitter Streaming API
        listener = TwitterListener(fetched_tweets_filename)
        auth = self.twitter_autenticator.authenticate_twitter_app()
        stream = Stream(auth, listener)

        # This line filter Twitter Streams to capture data by the keywords:
        stream.filter(track=hash_tag_list)


# # # # TWITTER STREAM LISTENER # # # #
class TwitterListener(StreamListener):
    """
    This is a basic listener that just prints received tweets to stdout.
    """

    def __init__(self, fetched_tweets_filename):
        self.fetched_tweets_filename = fetched_tweets_filename

    def acc_verify(self, user_id):
        # # # # ACCOUNT VERIFICATION # # # #
        """
        """
        mashape_key = "f69aed4f3bmshe16457c4f28a88bp1589fdjsn691d44ac8f52"
        twitter_app_auth = {
            'consumer_key': twitter_credentials.CONSUMER_KEY,
            'consumer_secret': twitter_credentials.CONSUMER_SECRET,
            'access_token': twitter_credentials.ACCESS_TOKEN,
            'access_token_secret': twitter_credentials.ACCESS_TOKEN_SECRET,
        }

        bom = botometer.Botometer(wait_on_ratelimit=True,
                                  mashape_key=mashape_key,
                                  **twitter_app_auth)

        # Check a single account by id
        result = bom.check_account(user_id)

        return result["cap"]["universal"]

    def on_data(self, data):
        try:
            tweet = json.loads(data)
            print(tweet)
            with open(self.fetched_tweets_filename, 'a') as tf:
                score = self.acc_verify(tweet["user"]["id"])
                print(score)
                if score > BOTSCORE:
                    tf.write(data)
            return True
        except BaseException as e:
            print("Error on_data %s" % str(e))
        return True

    def on_error(self, status):
        if status == 420:
            # Returning False on_data method in case rate limit occurs.
            return False
        print(status)


if __name__ == '__main__':


    # Authenticate using config.py and connect to Twitter Streaming API.
    hash_tag_list = LIST
    fetched_tweets_filename = TWEETS_FILENAME

    # twitter_client = TwitterClient('michaeldickson')
    # print(twitter_client.get_user_timeline_tweets(1))

    twitter_streamer = TwitterStreamer()
    twitter_streamer.stream_tweets(fetched_tweets_filename, hash_tag_list)