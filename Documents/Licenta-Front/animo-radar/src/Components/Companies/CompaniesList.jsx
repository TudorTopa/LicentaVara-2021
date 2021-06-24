

import React, { Component,  Fragment  } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {getCompanies, getCompanyDetails, getCompanyProjects} from '../../Services/CompanyService';
import { Pie } from "react-chartjs-2";
import { MDBContainer } from "mdbreact";
import './companis.scss';
import history from '../../Routes/history';
import { getCompanyTechnologies } from '../../Services/TechnologyService';
import { PieDataSet } from '../../DTO/PieChart/PieDataSet';

class CompaniesList extends Component {

    constructor(props){
        super(props)

        this.state = {
            companyDataAvailable: false,
            companies : [],
            companyProjects : [],
            companyDetails : "",
            companyDetailsState : false,
            companyProjectsState : false,
            companyTechnologies: []
        }

        this.pieStateTest = {
            dataPie: {
              labels: [],
              datasets: [
                {
                  data: [],
                  backgroundColor: [
                    "#F7464A",
                    "#46BFBD",
                    "#FDB45C",
                    "#949FB1",
                    "#4D5360",
                    "#AC64AD",
                    "#194d33",
                    "#1472e0",
                    "#580d16"
                  ],
                  hoverBackgroundColor: [
                    "#FF5A5E",
                    "#5AD3D1",
                    "#FFC870",
                    "#A8B3C5",
                    "#616774",
                    "#DA92DB",
                    "#669a80",
                    "#689ddb",
                    "#83595e"
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
        this.setState({companyDataAvailable: true})
    }

     fetchCompanyDetails(id) {
        getCompanyDetails(id).then((res) => {
            this.setState({companyDetails : res.data})
        })
        this.fetchCompanyProjects(id)
        this.fetchCompanyTechnologies(id)
    }

    fetchCompanyProjects(id) {
        getCompanyProjects(id).then((res) => {
            this.setState({companyProjects : res.data})
        })
    }

    processTechnologiesDataPie() {
        this.pieStateTest.dataPie.labels = []
        this.pieStateTest.dataPie.datasets[0].data = []
        for(var i = 0 ; i < this.state.companyTechnologies.length ; i++)
        {
        this.pieStateTest.dataPie.labels.push(this.state.companyTechnologies[i][1])
        this.pieStateTest.dataPie.datasets[0].data.push(this.state.companyTechnologies[i][2])
        }
        this.HandleCompanyDetailsChange()    
    }

    fetchCompanyTechnologies(id) {

        getCompanyTechnologies(id).then((res) => {
            this.setState({companyTechnologies : res.data});
            this.processTechnologiesDataPie();
        })
    }

    onAddProjectClick(){ 
        history.push("/projectRegistration")
    }

    onEmployeesClick(){
        history.push("/employees")
    }

     


    render() {
       
        return (
            <div className="content"> 
                <h2 className = "text-center" > Companies Radar</h2>
                     <div className="content">
                    <table class="table table-striped table-hover">
                        <thead  class="thead-light">
                            <tr > 
                                {/* logo */}
                                <th scope="col">CompanyId</th>
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
                                        <td>{2}</td>
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
                    this.state.companyDataAvailable && (
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
                                           <td>{project.startDate.slice(0,-19)}</td>
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
                     this.state.companyDataAvailable && (
                        <div class=" text-center justify-content-center ">
                            <h3>{this.state.companyDetails.companyName}'s technology radar overview</h3>
                            <Fragment>
                                <MDBContainer>
                                     <Pie data={this.pieStateTest.dataPie} options={{ responsive: true }} />
                                 </MDBContainer>
                             </Fragment>
                             <button onClick={() => this.onEmployeesClick()} type="button" class="btn btn-success btn-sm" >View Employees</button>
                        </div>
                    )
                }
                    
    </div>
    </div>

        )}
}

export default CompaniesList;