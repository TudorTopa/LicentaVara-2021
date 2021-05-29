import { combineReducers } from "redux";

import userReducer from "./userReducer.jsx";
import snackbarReducer from "./snackbarReducer";

const reducer = combineReducers({
  user: userReducer,
  snackbar: snackbarReducer,
});

export default reducer;