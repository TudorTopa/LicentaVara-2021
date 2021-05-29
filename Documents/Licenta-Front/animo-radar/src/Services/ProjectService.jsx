import axios from "axios";
import { AUTHORIZED_GET } from "../Actions/Utils";

const PROJECT_BASE_API_URL = "/api/projects"

    export const getProjects = () => {
        return AUTHORIZED_GET(`${PROJECT_BASE_API_URL}`)
    }



export default {getProjects}