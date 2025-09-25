import axios from "axios";
import { USER_PROFILE_CONTROLLER_URL } from "../Utilities/const";

async function deleteUser(){
    try{
        const response = await axios.delete(USER_PROFILE_CONTROLLER_URL);
        return response.data;
    } catch(e){
        console.error(e);
    }
    return null;
}

export default deleteUser;