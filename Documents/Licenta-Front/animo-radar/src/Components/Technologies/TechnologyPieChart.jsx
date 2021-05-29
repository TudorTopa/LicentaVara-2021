import React, { Fragment } from "react";
import { Pie } from "react-chartjs-2";
import { MDBContainer } from "mdbreact";

class TechnologyPieChart extends React.Component {
  state = {
    dataPie: {
      labels: ["Java", ".Net", "C++", "JavaScript", "Python"],
      datasets: [
        {
          data: [200, 50, 100, 40, 120],
          backgroundColor: [
            "#F7464A",
            "#46BFBD",
            "#FDB45C",
            "#949FB1",
            "#4D5360",
            "#AC64AD"
          ],
          hoverBackgroundColor: [
            "#FF5A5E",
            "#5AD3D1",
            "#FFC870",
            "#A8B3C5",
            "#616774",
            "#DA92DB"
          ]
        }
      ]
    }
  }
  state2 = {
      dataPie2: {
          labels: ["SQLServer", "PostgreSQL", "Mongodb", "MySQL"],
         datasets: [
        {
          data: [150, 90, 20, 240],
          backgroundColor: [
            "#F7464A",
            "#46BFBD",
            "#FDB45C",
            "#949FB1",
            "#4D5360"
          ],
          hoverBackgroundColor: [
            "#FF5A5E",
            "#5AD3D1",
            "#FFC870",
            "#A8B3C5",
            "#616774"
          ]
        }
      ]
      }
  }

  render() {
    return (
    <Fragment>
      <MDBContainer>
        <h3 className="mt-5">Technology Radar</h3>
        <h4 className="mt-5">Programming Languages</h4>
        <Pie data={this.state.dataPie} options={{ responsive: true }} />
      </MDBContainer>
     </Fragment>
    );
  }
}


export default TechnologyPieChart;