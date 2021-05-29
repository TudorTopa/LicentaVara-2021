import { AUTHORIZED_GET } from "../Actions/Utils";

const TECHNOLOGY_BASE_API_URL = "/api/technologies"

    export const getTechnologies = () => {
        return AUTHORIZED_GET(`${TECHNOLOGY_BASE_API_URL}`)
    }
    export const getTechnologiesByCategory = (category) => {
        return AUTHORIZED_GET(`${TECHNOLOGY_BASE_API_URL}/category/${category}`)
    }


export default {getTechnologies,getTechnologiesByCategory}