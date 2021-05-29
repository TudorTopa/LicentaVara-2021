import React, { Fragment } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { resetNotificationTrigger } from "../../Actions/Actions";

import NotificationAlert from "react-notification-alert";

class SnackBar extends React.Component {
  shouldNotify = () => {
    return this.props.snackbar.trigger === true;
  };

  notify = () => {
    this.refs.notificationAlert.notificationAlert(this.props.snackbar.options);
    this.props.resetNotificationTrigger();
  };

  render() {
    return (
      <Fragment>
        <div className="react-notification-alert-container">
          <NotificationAlert ref="notificationAlert" />
        </div>

        {this.shouldNotify() && this.notify()}
      </Fragment>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    snackbar: state.snackbar,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    ...bindActionCreators({ resetNotificationTrigger }, dispatch),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(SnackBar);
