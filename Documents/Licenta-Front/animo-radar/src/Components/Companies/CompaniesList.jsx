

import React, { Component,  Fragment  } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {getCompanies, getCompanyDetails, getCompanyProjects} from '../../Services/CompanyService';
import { Pie } from "react-chartjs-2";
import { MDBContainer } from "mdbreact";
import './companis.scss';
import history from '../../Routes/history';
class CompaniesList extends Component {

    constructor(props){
        super(props)

        this.state = {
            companies : [],
            companyProjects : [],
            companyDetails : "",
            companyDetailsState : false,
            companyProjectsState : false
        }

        this.pieStateTest = {
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


    }

    componentDidMount(){
        getCompanies().then((res) => {
            this.setState({companies : res.data});
        })
    }

    HandleCompanyDetailsChange(){
        this.setState({companyDetailsState:!this.state.companyDetailsState})
        this.setState({companyProjectsState:!this.state.companyProjectsState})
    }


    fetchCompanyDetails(id) {
        getCompanyDetails(id).then((res) => {
            this.setState({companyDetails : res.data})
            console.log(this.state.companyDetails)
        })
        this.fetchCompanyProjects(id)
        this.HandleCompanyDetailsChange()
    }
    fetchCompanyProjects(id) {
        getCompanyProjects(id).then((res) => {
            this.setState({companyProjects : res.data})
        })
    }
    onAddProjectClick(){
        history.push("/projectRegistration")
    } 
     


    render() {
       
        const CompanyDetails = this.state.companyDetailsState;
        const CompanyProjects = this.state.companyProjectsState;

        return (
            <div className="content"> 
                <h2 className = "text-center" > Companies Radar</h2>
                     <div className="content">
                    <table class="table table-striped table-hover">
                        <thead  class="thead-light">
                            <tr > 
                                {/* logo */}
                                <th scope="col">Logo</th>
                                {/* logo */}
                                <th scope="col"> Company name</th>
                                <th scope="col"> Employees</th>
                                <th scope="col"> Projects</th>
                                <th scope="col"> Foundation Date</th>
                                <th scope="col"> Services</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.companies.map(
                                    company =>
                                    <tr className="table-row" key = {company.companyId} onClick={() => this.fetchCompanyDetails(company.companyId)}>
                                        <td>{company.companyId}</td>
                                        <td>{company.companyName}</td>
                                        <td><p>1</p></td>
                                        <td>{company.projects.length }</td>
                                        <td>{company.foundationDate}</td>
                                        <td><p>Outsourcing</p></td>
                                    </tr>

                                )
                            }
                        </tbody>

                    </table>
                </div>
                <div className='rowC'>
                

                {
                    CompanyProjects && (
                        <div class="row justify-content-center">
                             <h3 className = "text-center" > Project's overview</h3>
                         <table className="table table-striped table-hover">
                           <thead  class="thead-light">
                               <tr > 
                                   {/* logo */}
                                   <th scope="col">Project Id</th>
                                   {/* logo */}
                                   <th scope="col"> Project name</th>
                                   <th scope="col"> Start Date</th>
                                   <th scope="col"> isFinished</th>
                               </tr>
                           </thead>
                           <tbody>
                               {
                                   this.state.companyProjects.map(
                                       project =>
                                       <tr  key = {project.projectId}>
                                           <td>{project.projectId}</td>
                                           <td>{project.name} </td>
                                           <td><p>2019-1-13</p></td>
                                           <td>true</td>
                                       </tr>
   
                                   )
                               }
                           </tbody>
   
                       </table>
                       <button onClick={() => this.onAddProjectClick()} type="button" class="btn btn-success btn-sm" >Add Project</button>

            </div>
        )
    }
      {
                    CompanyDetails && (
                        <div class=" text-center justify-content-center ">
                            
                            <h3>{this.state.companyDetails.companyName}'s technology radar overview</h3>
                            <Fragment>
                                <MDBContainer>
                                     <Pie data={this.pieStateTest.dataPie} options={{ responsive: true }} />
                                 </MDBContainer>
                             </Fragment>
                        </div>
                    )
                }
    </div>
    </div>

        )}
}

export default CompaniesList;