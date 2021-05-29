import React, { Component } from 'react';
import { getProjects } from '../../Services/ProjectService';

class Projects extends Component {

    constructor(props){
        super(props)

        this.state = {
            projects : [],
            
        }
    }

    componentDidMount(){
        getProjects().then((res) => {
            this.setState({projects : res.data});
        })
    }

    render() {
        return (
            <div>
                <h2>Project</h2>
                <table class="table table-striped table-hover">
                <thead>
                <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Is Finished</th>
                <th scope="col">Start Date</th>
                </tr>
            </thead>
            <tbody>
            {
            this.state.projects.map(
                                       project =>
                                       <tr  key = {project.projectId}>
                                           <td>{project.projectId}</td>
                                           <td>{project.name} </td>
                                           <td><p>true</p></td>
                                           <td>2020-09-10</td>
                                       </tr>
   
                                   )
            }
            </tbody>
            </table>
            </div>
        );
    }
}

export default Projects;