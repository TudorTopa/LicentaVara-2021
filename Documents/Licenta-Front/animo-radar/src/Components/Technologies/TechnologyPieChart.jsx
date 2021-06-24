import React, { Fragment } from "react";
import { Pie } from "react-chartjs-2";
import { MDBContainer } from "mdbreact";
import { getTechnologiesByCategory,getTechnologiesByState, getTechnologiesByStateAndCompany} from "../../Services/TechnologyService";
import SplitPane from "react-split-pane";
import './pieChart.scss'
import { getCompanies } from "../../Services/CompanyService";
import e from "cors";

class TechnologyPieChart extends React.Component {
  
  state = {
    pieChartState : false,
    fetchedTechnologies : true,
    selectedState : "TRIAL",
    companies : [{companyId:'All',companyName:'All'}],
    selectedCompany : "51",

    technologyState :  [
      
      {id:'ADOPT',label:'Adopt'},
      {id:'ASSESS',label:'Asses'},
      {id:'HOLD',label:'Hold'},
      {id:'TRIAL',label:'Trial'},
  ],
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


  componentDidMount(){
    getCompanies().then((res) => {
      this.setState({ companies: [...this.state.companies, ...res.data] }) 
    })
    getTechnologiesByState("ADOPT").then((res) =>
    { 
      for(var i = 0 ; i < res.data.length ; i++ ){
       this.state.dataPie.labels.concat(res.data[i][0]);
      }
    },
    )
  }

  addNewItem = (item,index) => {
    var someProperty = {...this.state.dataPie}
    someProperty.labels =  someProperty.labels.concat(item[index][0]);
    someProperty.datasets[0].data = someProperty.datasets[0].data.concat(item[index][1])
    this.setState({dataPie: someProperty},
      )
  };
  
  async fetchTechnologies(){
    if(this.state.selectedCompany === 'All'){
      getTechnologiesByState(this.state.selectedState).then((res) =>{
        if(res.data.length === 0){
          this.setState({fetchedTechnologies:false})
          console.log("technologiesFetched", this.state.fetchedTechnologies)
        }
        else{
          this.setState({fetchedTechnologies:true})
        for(var i = 0 ; i < res.data.length ; i++ ){
        this.addNewItem(res.data,i)
      }}})}
    else{
      getTechnologiesByStateAndCompany(this.state.selectedState,this.state.selectedCompany).then((res) =>{
        if(res.data.length === 0){
          this.setState({fetchedTechnologies:false})
          console.log("technologiesFetched", this.state.fetchedTechnologies)
        }
        else{
          this.setState({fetchedTechnologies:true})
        for(var i = 0 ; i < res.data.length ; i++ ){
        this.addNewItem(res.data,i)
        }}})
  }
}


  clearDataPie(){
    var someProperty = {...this.state.dataPie}
    someProperty.labels =  [];
    someProperty.datasets[0].data = []
    this.setState({dataPie: someProperty})
  }

  onCategorySelect = async technologyState => {
    await this.setState({pieChartState : true})
    await this.setState({selectedState : technologyState.target.value})
    this.clearDataPie()
    this.fetchTechnologies()

  }
  onCompanySelect = async company => {
    await this.setState({pieChartState : true})
    await this.setState({selectedCompany : company.target.value})
    this.clearDataPie()
    this.fetchTechnologies()
 
  } 

  

  render() {
    const pieChart = this.state.pieChartState;
    const technologiesFetched = this.state.fetchedTechnologies;

    return (
      <SplitPane split="vertical" defaultSize={200} primary="second">

        <div>
          {pieChart && technologiesFetched && (<div>
            <Fragment>
              <MDBContainer>
                <h3 className="mt-5">Technology Radar</h3>
                <h4 className="mt-5">Overview of {this.state.selectedState} category technologies</h4>
                <Pie data={this.state.dataPie} options={{ responsive: true }} />
              </MDBContainer>
            </Fragment>      
          </div>) 
         }
         {
           !technologiesFetched && (<div>
             <h3 className="title">No {this.state.selectedState} technologies registered for this company </h3>
             </div>)
         }
        </div>

      <div class="select-input">
       <label class= "label">
           Technology Category
           <select class="form-control" onChange={this.onCategorySelect}>
           {
              this.state.technologyState.map(
                                    state =>(
                                    <option selected value = {state.id}>{state.label}</option>        
                                ))}
           </select>
        </label>
        <label class= "label">
           Company
           <select class="form-control" onChange={this.onCompanySelect}>
           {
             this.state.companies.map(
              company =>(
              <option selected value = {company.companyId}>{company.companyName}</option>
              ))
          }
           </select>
        </label>
        </div>
       
    
     </SplitPane>
     
    );
  }
}


export default TechnologyPieChart;