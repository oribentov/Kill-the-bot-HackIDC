import React, {Component} from 'react';
import {hot} from 'react-hot-loader';
import {Route, Switch} from 'react-router-dom';
import {blue, red} from '@material-ui/core/colors';
import {MuiThemeProvider, createMuiTheme} from '@material-ui/core/styles';
import HelloWorld from './components/hello-world';
import SearchAppBar from './components/SearchNavBar';
import MainPage from './components/MainPage';
import AddNewProduct from './components/Products/AddNewProduct/addNewProduct';
import About from './components/About';
import 'typeface-roboto';
import purple from "@material-ui/core/es/colors/purple";
import UserRegistartion from './components/SignUp/userRegistration';
import Chat from "./components/Chat/Chat";
import 'App.css'

const theme = createMuiTheme({
    typography: {
        useNextVariants: true,
        fontFamily: "'Open Sans', sans-serif;"

    },
    button: {
        color: "white",
       
    },

    palette: {
        primary: {
            light: '#757ce8',
            main: blue[500],
            dark: '#002884',
            contrastText: '#fff',
           
        },
        secondary: {
            light: '#ff7961',
            hover: blue[700], 
            main: blue[100],
            dark: '#ba000d',
            contrastText: '#000',
        },
    },
});

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <MuiThemeProvider theme={theme}>
                <div>
                    <SearchAppBar/>
                    <Switch>
                        <Route exact path="/" exact component={MainPage}/>
                        <Route path="/About" exact component={About}/>
                        <Route path="/NewProduct" exact component={AddNewProduct}/>
                        <Route path="/Hello/:title" exact component={HelloWorld}/>
                        <Route path="/Registration" exact component={UserRegistartion} />
                    </Switch>
                </div>
            </MuiThemeProvider>
        );
    }
}

export default hot(module)(App);
