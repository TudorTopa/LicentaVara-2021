import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './projects.scss'
import { getCompanies } from '../../Services/CompanyService';
import { getTechnologiesByCategory } from '../../Services/TechnologyService';

import history from '../../Routes/history';
import { Multiselect } from 'multiselect-react-dropdown';

const technologyCategories = 
[
   { id: 'LANGUAGE', name: 'Language' },
   { id: 'BACK_END', name: 'Back end' },
   { id: 'FRONT_END', name: 'Front end' },
   { id:'DATABASE',name:'Database'},
   { id:'INTEGRATION',name:'Integration tools'},
   { id:'AI',name:'Artificial intelligence'},
   { id:'VERSION_CONTROL',name:'Version control'},
   { id:'COMPUTER_NETWORKS',name:'Computer Networks'},
]



class ProjectForm extends Component {

  constructor(props){
    super(props)

    this.state = {
    projectName: "",
    technologyCategories : technologyCategories,
    selectedCategory : "",
    selectedCompany: "",
    selectedTechnologies :[],
    requiredNoEmployees: 0,
    recommendedNoEmployees: 0,
    technologies : [],
    companies : [],
    startDate : new Date()
  }

}




componentDidMount(){
  getCompanies().then((res) => {
    this.setState({companies : res.data});
})
}

addToArray = (a,b) => {
  if(a.length === 0){
    return b;
  }
  else if(b.length === 0){
    return a;
  }
  else{
  return this.arrayUnique(a.concat(b));
  }
}
 removeItem = (arr, value) => {
  for(var i = 0 ; i < arr.length ; i++){
    if(JSON.stringify(arr[i]) === JSON.stringify(value)){
      var index = i;
      if (index > -1) {
        arr.splice(index, 1);
      }
    }
  }
  return arr;
}

  setCategory = async category => {
    await this.setState({selectedCategory: category.target.value});
    this.getTechnologies();
  }
  setCompany = async company => {
    await this.setState({selectedCompany: company.target.value})
    console.log(this.state.selectedCompany)
  }
  setTechnologies = async (selectedList,selectedItem) => {
    
      this.state.selectedTechnologies.push(selectedItem.technologyId)

    this.state.selectedTechnologies = this.arrayUnique(this.state.selectedTechnologies)
    console.log("selectedTechnologies:" + JSON.stringify(this.state.selectedTechnologies))
  }
  deleteTechnologies = async (selectedList, removedItem) => {
    this.state.selectedTechnologies = this.removeItem(this.state.selectedTechnologies, removedItem.technologyId)
    console.log("selectedTechnologies:" + JSON.stringify(this.state.selectedTechnologies))
  } 
  setRequiredNoEmployees = async requiredEmployees => {
    await this.setState({requiredNoEmployees:requiredEmployees.target.value})
    console.log("RequiredNumberOfEmployees: ", this.state.requiredNoEmployees)
  }
  setRecommendedNoEmployees = async recommendedEmployees => {
    await this.setState({recommendedNoEmployees:recommendedEmployees.target.value})
    console.log("RecommendedEmployees", this.state.recommendedEmployees)
  }

  arrayUnique(array) {
    var a = array.concat();
    for(var i=0; i<a.length; ++i) {
        for(var j=i+1; j<a.length; ++j) {
            if(a[i] === a[j]){  
              a.splice(j--, 1);
            }
        }
    }
    return a;
}


  removeFromArray = (myArray,toRemove) => {
      for(var j = 0; j <toRemove.length; j++){
      myArray = this.removeItem(myArray,toRemove[j]);
    }
    return myArray
    } ;
  
 
  getTechnologies =  async () =>{
    getTechnologiesByCategory(this.state.selectedCategory).then((res) => {
      this.setState({technologies: this.addToArray(res.data,this.state.technologies)});
    })
  }

  moveToTeamCreation(){
    history.push(
      {
        pathname : "/projectTeamCreation",
        state : this.state
      })
  }

  removeTechnologies = async () =>{
    getTechnologiesByCategory(this.state.selectedCategory).then((res) => {
     this.setState({technologies: this.removeFromArray(this.state.technologies,res.data)})
      });
  }

  onSelect = async (selectedList, selectedItem) =>{
    console.log("Selected category:",selectedItem)  
    await this.setState({selectedCategory : selectedItem.id})
    this.getTechnologies()
  }

  onRemove =  async (selectedList, removedItem) => {
   await this.setState({selectedCategory : removedItem.id})
    this.removeTechnologies()
  }

  setProjectName = async (projectName) => {
    await this.setState({projectName : projectName.target.value})
    console.log("Project Name set", this.state.projectName)
  }

  setStartDate = async (startDate) => {
    await this.setState({startDate : startDate.target.value})
    console.log("Start date set", this.state.startDate)
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
              <div class="form-group col-md-6">
                <label for="inputEmail4">Project Name</label>
                <input type="text" class="form-control" id="projectName" placeholder="Project Name" onChange={this.setProjectName}/>
              </div>
              <div class="form-group col-md-6">
                <label for="company">Company</label>
                <select id="company" class="form-control" onChange={this.setCompany}>
                {
                                this.state.companies.map(
                                    company =>(
                                    <option selected value = {company.companyId}>{company.companyName}</option>
                                    
                                ))}
                </select>
            </div>
           
            <div class="form-group col-md-6">
            <label for="">Technology Categories</label>
                      <Multiselect
                      options={technologyCategories} // Options to display in the dropdown
                      selectedValues={null} // Preselected value to persist in dropdown
                      onSelect={this.onSelect} // Function will trigger on select event
                      onRemove={this.onRemove} // Function will trigger on remove event
                      displayValue="name" // Property name to display in the dropdown options
                      />

       
            </div>
            
              <div class="form-group col-md-6">
                <label for="technology">Technology</label>
                <Multiselect
                      options={this.state.technologies} // Options to display in the dropdown
                      selectedValues={null} // Preselected value to persist in dropdown
                      onSelect={this.setTechnologies} // Function will trigger on select event
                      onRemove={this.deleteTechnologies} // Function will trigger on remove event
                      displayValue="technologyName" // Property name to display in the dropdown options
                      />

              </div>
              <h5 className="title">Team Members</h5>
            <div class="form-group col-md-4">
            <label for="requiredEmployees">Required</label>
              <input type="number" class="form-control" id="requiredEmployees" placeholder="Required Employees" onChange={this.setRequiredNoEmployees}/> 
            </div>
            <div class="form-group col-md-4">
            <label for="recomendedEmployees">Recomended</label>
              <input type="number" class="form-control" id="recomendedEmployees" placeholder="Recomended Employees" onChange={this.setRecommendedNoEmployees}/>
             </div>
            
              <div class="form-group col-md-8">
              <label for="projectStartDate">Start Date</label>                
                <input type="date" class="form-control" id="projectStartDate" onChange={this.setStartDate}/>
              </div>
            </div>
          </form>
          <div class="span2">
            <p><button onClick={() => this.moveToTeamCreation()} class="btn btn-success btn-block" >Create team</button></p>
          </div>  
       </div>
       </div>
      </div>
        );
    }
}



export default ProjectForm;

