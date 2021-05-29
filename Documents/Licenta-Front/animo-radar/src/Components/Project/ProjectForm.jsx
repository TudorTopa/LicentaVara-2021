import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './projects.scss'
import { getCompanies } from '../../Services/CompanyService';
import { getTechnologiesByCategory } from '../../Services/TechnologyService';

import Table, {Thead, Tbody, Tr, Th, Td} from "react-row-select-table"

const technologyCategories = 
[
   { value: 'LANGUAGE', label: 'Language' },
   { value: 'BACK_END', label: 'Back end' },
   { value: 'FRONT_END', label: 'Front end' },
 ];  


class ProjectForm extends Component {

  constructor(props){
    super(props)

    this.state = {
    technologyCategories : technologyCategories,
    selectedCategory : "LANGUAGE",
    technologies : [],
    companies : []
  }
}


componentDidMount(){
  getCompanies().then((res) => {
    this.setState({companies : res.data});
    console.log("Companies" , this.state.companies)
})
}

  setCategory = category => {
    this.setState({selectedCategory: category.target.value});
    console.log('Category selected ', this.state.selectedCategory);
    this.getTechnologies();
  }

  getTechnologies(){
    getTechnologiesByCategory(this.state.selectedCategory).then((res) => {
      this.setState({technologies:res.data});
      console.log("Technologies fetched", this.state.technologies)
    })
  }
  

    render() {
  
        return (
          <div className="splitScreen">
          <div className="topPane">
            <div className="form-content">
                <h2 className = "text-left"> Add new project</h2>
            <form>
            <div class="row">
              <h5>Project Details</h5>
              <div class="form-group col-md-4">
                <label for="inputEmail4">Project Name</label>
                <input type="text" class="form-control" id="projectName" placeholder="Project Name"/>
              </div>
              <div class="form-group col-md-4">
                <label for="company">Company</label>
                <select id="company" class="form-control" value="company">
                {
                                this.state.companies.map(
                                    company =>(
                                    <option selected value = {company.companyId}>{company.companyName}</option>
                                    
                                ))}
                </select>
            </div>
           
            <div class="form-group col-md-4">
            <label for="technologyCategory">Technology Category</label>
              <select id="technologyCategory" class="form-control" onChange={this.setCategory} >
              {
                                this.state.technologyCategories.map(
                                    technologyCategory =>(
                                    <option  value = {technologyCategory.value}>{technologyCategory.label}</option>
                                    
                                ))}
              </select>
            </div>
            
              <div class="form-group col-md-4">
                <label for="technology">Technology</label>
                <select id="technology" class="form-control">
                 {
                   this.state.technologies.map(
                     technology => (
                       <option value={technology.technologyId}>{technology.technologyName}</option>
                     )
                   )
                 }
                </select>
              </div>
              <h5 className="title">Team Members</h5>
            <div class="form-group col-md-4">
            <label for="requiredEmployees">Required</label>
              <input type="number" class="form-control" id="requiredEmployees" placeholder="Required Employees"/> 
            </div>
            <div class="form-group col-md-4">
            <label for="recomendedEmployees">Recomended</label>
             <input type="number" class="form-control" id="recomendedEmployees" placeholder="Recomended Employees"/>
             </div>
            
              <div class="form-group col-md-8">
              <label for="projectStartDate">Start Date</label>                
                <input type="date" class="form-control" id="projectStartDate"/>
              </div>
            </div>
          </form>
          <div class="span2">
            <p><button class="btn btn-success btn-block">Add</button></p>
          </div>  
       </div>
       </div>
       <div className="bottomPane"></div>
       <Table onCheck={value => console.log(value)} defaultCheckeds={[1,3]}>
    <Thead>
      <Tr>
        <Th>id</Th>
        <Th>name</Th>
      </Tr>
    </Thead>
    <Tbody>
      <Tr>
        <Td>1</Td>
        <Td>tarou</Td>
      </Tr>
      <Tr>
        <Td>2</Td>
        <Td>zirou</Td>
      </Tr>
      <Tr>
        <Td>3</Td>
        <Td>subrou</Td>
      </Tr>
    </Tbody>
  </Table>

       </div>
        );
    }
}



export default ProjectForm;

