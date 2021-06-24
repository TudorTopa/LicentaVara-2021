import { AUTHORIZED_GET } from "../Actions/Utils";

const TECHNOLOGY_BASE_API_URL = "/api/technologies"
const PROJECT_TECHNOLOGIES_BASE_API_URL = "/api/projectTechnologies"

    export const getTechnologies = () => {
        return AUTHORIZED_GET(`${TECHNOLOGY_BASE_API_URL}`)
    }
    export  const getTechnologiesByCategory =  (category) => {
        console.log("Technology category get", category)
        return AUTHORIZED_GET(`${TECHNOLOGY_BASE_API_URL}/category/${category}`)
    }
    export const getTechnologiesCountByCategory =  (category) => {
        console.log("Technology category get", category)
        return AUTHORIZED_GET(`${TECHNOLOGY_BASE_API_URL}/categoryCount/${category}`)
}
    export const getCompanyTechnologies = (id) => {
        return AUTHORIZED_GET(`${PROJECT_TECHNOLOGIES_BASE_API_URL}/common/company/${id}`)
    }
    export const getTechnologyById = (id) => {
        return AUTHORIZED_GET(`${TECHNOLOGY_BASE_API_URL}/${id}`)
    }
    export const getTechnologiesByState = (id) => {
        return AUTHORIZED_GET(`${PROJECT_TECHNOLOGIES_BASE_API_URL}/state/${id}`)
    }
    export const getTechnologiesByStateAndCompany = (state,company) => {
        return AUTHORIZED_GET(`${PROJECT_TECHNOLOGIES_BASE_API_URL}/state/${state}/company/${company}`)

    }



export default {getTechnologies,getTechnologiesByCategory,getCompanyTechnologies,getTechnologyById,getTechnologiesByState,getTechnologiesCountByCategory,getTechnologiesByStateAndCompany}