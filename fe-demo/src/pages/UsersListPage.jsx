import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { UsersApi } from "../api/usersApi";
import UserItem from "./UserItem";

export default function UsersListPage() {
    const [users, setUsers] = useState([]);

    async function fetchUsers() {
        const data = await UsersApi.listUsers();
        setUsers(data);
    }

    useEffect(() => { fetchUsers(); }, []);

    return (
        <div style={{ maxWidth: 860, margin: "0 auto", padding: 24 }}>
        <h1>Usuários</h1>

        <Link to="/users/new"><button>+ Novo Usuário</button></Link>

        <ul style={{ padding: 0, listStyle: "none", marginTop: 20 }}>
            {users.map(user => (
            <li key={user.id}>
                <UserItem user={user} reload={load} />
            </li>
            ))}
        </ul>
        </div>
    ); 
}