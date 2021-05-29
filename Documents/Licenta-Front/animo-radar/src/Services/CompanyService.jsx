import axios from "axios";
import { AUTHORIZED_GET } from "../Actions/Utils";


const COMPANY_BASE_API_URL = "/api/companies"




    export const getCompanies = () => {
        return AUTHORIZED_GET(`${COMPANY_BASE_API_URL}`)
}
    export const getCompanyDetails = (id) => {
        return AUTHORIZED_GET(`${COMPANY_BASE_API_URL}/${id}`)
    }
    export const getCompanyProjects = (id) => {
        return AUTHORIZED_GET(`${COMPANY_BASE_API_URL}/${id}/projects`)
    }

export default {getCompanies, getCompanyDetails, getCompanyProjects}