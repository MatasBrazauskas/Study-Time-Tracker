import { useNavigate } from "react-router-dom";
import { getYearsActivity } from "../APIs/userActivityAPIs";

function UserActivityPage(){

    const temp = async () => await getYearsActivity(new Date().getFullYear());
    const navigation = useNavigate();

    return (
    <div>
        <button onClick={() => navigation('/clock')}>Clock Page</button>
        <button onClick={() => temp()}>Get Years Activity</button>        
    </div>)
}

export default UserActivityPage;