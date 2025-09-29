import { useNavigate } from "react-router-dom";

import './userStyle.css';

function UserActivityPage(){

    const navigation = useNavigate();

    return (
        <div className='activity'>
            <button onClick={() => navigation('/clock')}>Clock Page</button>
        </div>)
}

export default UserActivityPage;