import axios from "axios";
import { USER_PROFILE_CONTROLLER_URL } from "../Utilities/const";

async function getCookies(): Promise<any>{

    try{
        const response = await axios.get(USER_PROFILE_CONTROLLER_URL);
        console.table(response.data);
        return response.data;
    } catch(e){
        console.error(e);
    }
    return null;
}

export default getCookies;