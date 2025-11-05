import { Link } from "react-router-dom";
import { UsersApi } from "../api/usersApi";

export default function UserItem({ user, reload }) {
    async function handleDelete() {
        const confirmed = window.confirm(`Tem certeza que deseja excluir o usuário ${user.username}?`);
        if (confirmed) {
            await UsersApi.deleteUser(user.id);
            reload();
        }
    }

    return (
        <article style={{ border: "1px solid #ddd", padding: 16, marginBottom: 10 }}>
            <h3>{user.username}</h3>
            <p>{user.id}</p>

            <Link to={`/users/${user.id}/edit`}><button>Editar</button></Link>
            <button onClick={handleDelete} style={{ marginLeft: 10 }}>Excluir</button>
        </article>
  );
}