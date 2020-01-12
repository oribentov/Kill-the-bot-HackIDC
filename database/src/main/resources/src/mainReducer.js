import { combineReducers } from 'redux';
import newProductReducer from './components/Products/AddNewProduct/newProductReducer';
import userRegistrationReducer from './components/SignUp/userRegistrationReducer';
import searchNavBarReducer from './components/SearchNavBar/searchNavBarReducer';
import loginReducer from './components/Login/loginReducer';
import productsReducer from './components/Products/productsReducer';

const mainReducer = combineReducers({
  newProductReducer,
  userRegistrationReducer,
  searchNavBarReducer,
  productsReducer,
  loginReducer,
});

export default mainReducer;
