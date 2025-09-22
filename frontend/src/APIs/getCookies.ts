import axios from "axios";
import { USER_PROFILE_CONTROLLER_URL } from "../Utilities/const";

async function getCookies(): Promise<any>{

    try{
        //await axios.get(USER_PROFILE_CONTROLLER_URL);
        const response = await fetch(USER_PROFILE_CONTROLLER_URL, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Content-Type":"application/json"
            }
        })
    } catch(e){
        console.error(e);
    }
    return null;
}

export default getCookies;