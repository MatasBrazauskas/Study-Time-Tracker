import axios from "axios";
import { LOG_IN_CONTROLLER_URL, REGISTER_CONTROLLER_URL} from "../Utilities/const";
import { type UsersInfo } from '../Utilities/types';

export async function RegisterUser(data: UsersInfo) : Promise<any>
{
    try{
        const response = await axios.post(REGISTER_CONTROLLER_URL, data);

        if(response.status === 200){
            return response.data;
        }
        
    } catch(e){
        console.error(e);
    }

    return null;
}

export async function LogInUser(data: UsersInfo): Promise<any>{
    try{
        const response = await axios.post(LOG_IN_CONTROLLER_URL, data);

        if(response.status === 200){
            return response.data;
        }
        
    } catch(e){
        console.error(e);
    }

    return null;
}