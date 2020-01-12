import time
import botometer
import twitter_credentials
import json
start_time = time.time()

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

# Check a single account by screen name
# result = bom.check_account('@DallasTopNews')
# print(result)

# Check a single account by id
result = bom.check_account(923598653511450624)
print(result)
print("--- %s seconds ---" % (time.time() - start_time))
# print (result["scores"]["universal"])
# print(type(acc["scores"]["universal"]))
            # print(tweet["user"]["id"])
# Check a sequence of accounts
# Accounts = ['@clayadavis', '@onurvarol', '@jabawack']
# for screen_name, result in bom.check_accounts_in(accounts):
#      Do stuff with `screen_name` and `result`