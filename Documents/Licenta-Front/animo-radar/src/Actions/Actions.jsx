import {POST}  from "./Utils";

  
const userLogin = (user) => {
    return POST('/auth/signin', user)
      .then((response) => {
        localStorage.setItem("jwt", response.data.token);
        localStorage.setItem("role", response.data.roles[0]);

  
});
}

   const userRegistration = (user) => {
      return POST('/auth/signup', user)
         .then((response) => {
           let options = {
             message: "Account created successfully.",
             type: "success",
             //icon: "tim-icons icon-check-2",
             autoDismiss: 7,
           };
         }) 
   };


  export const resetNotificationTrigger = () => {
    return {
      type: "RESET_TRIGGER",
    };
  };
  export const setNotification = (options) => {
    return {
      type: "SET_NOTIFICATION",
      payload: {
        options: options,
      },
    };
  };


  export  {userLogin, userRegistration};