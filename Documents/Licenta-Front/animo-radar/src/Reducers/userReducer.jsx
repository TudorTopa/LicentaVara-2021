const initialUserState = {
    username: "",
    company: ""
  };
  
  const userReducer = (state = initialUserState, action) => {
    switch (action.type) {
      case "LOGIN_SUCCESS": {
        return {
          ...state,
          username: action.payload.username,
        };
      }
  
      case "COMPANY_DATA": {
        return {
          ...state,
          company: action.payload.company,
        };
      }
  
      default: {
        return state;
      }
    }
  };
  
  export default userReducer;