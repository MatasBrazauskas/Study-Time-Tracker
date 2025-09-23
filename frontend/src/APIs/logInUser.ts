import axios from "axios";
import { USER_PROFILE_CONTROLLER_URL } from "../Utilities/const";

async function LogInUser(data: any) : Promise<any>
{
    try{
        const response = await axios.post(USER_PROFILE_CONTROLLER_URL, data);

        if(response.status === 200){
            return response.data;
        }
        
    } catch(e){
        console.error(e);
    }

    return null;
}

export default LogInUser;