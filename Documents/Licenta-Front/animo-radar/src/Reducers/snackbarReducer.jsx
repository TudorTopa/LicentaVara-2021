const initialSnackbarState = {
    trigger: false,
    options: {
      place: "br", // bottom-right
      message: "",
      type: "", // e.g. primary, success, danger, warning, info
      icon: "", // e.g. tim-icons icon-bell-55
      autoDismiss: 7, // time before auto-hide (in seconds)
    },
  };
  
  const snackbarReducer = (state = initialSnackbarState, action) => {
    switch (action.type) {
      case "LOGIN_SUCCESS": {
        return {
          trigger: true,
          options: {
            ...state.options,
            message: action.payload.message,
            type: action.payload.type,
            icon: action.payload.icon,
            autoDismiss: action.payload.autoDismiss,
          },
        };
      }
  
      case "LOGIN_ERROR": {
        return {
          trigger: true,
          options: {
            ...state.options,
            message: action.payload.message,
            type: action.payload.type,
            icon: action.payload.icon,
            autoDismiss: action.payload.autoDismiss,
          },
        };
      }
  
  
      case "SET_NOTIFICATION": {
        return {
          ...state,
          trigger: true,
          options: {
            ...state.options,
            message: action.payload.options.message,
            type: action.payload.options.type,
            icon: action.payload.options.icon,
            autoDismiss: action.payload.options.autoDismiss,
          },
        };
      }
  
      case "RESET_TRIGGER": {
        return {
          ...state,
          trigger: false,
        };
      }
  
      default: {
        return state;
      }
    }
  };
  
  export default snackbarReducer;