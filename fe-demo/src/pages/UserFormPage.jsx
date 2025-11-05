import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { UsersApi } from "../api/usersApi";

export default function UserFormPage() {
  const { id } = useParams();
  const editing = !!id;
  const navigate = useNavigate();

  const [values, setValues] = useState({ username: ""});

  useEffect(() => {
    if (editing) {
      UsersApi.getUser(id).then(setValues);
    }
  }, [editing, id]);

  async function handleSubmit(e) {
    e.preventDefault();
    if (editing) {
      await UsersApi.updateUser(id, values);
    } else {
      await UsersApi.createUser(values);
    }
    navigate("/users");
  }

  return (
    <div style={{ maxWidth: 600, margin: "0 auto", padding: 24 }}>
      <h1>{editing ? "Editar usuário" : "Novo usuário"}</h1>

      <form onSubmit={handleSubmit} style={{ display: "grid", gap: 12 }}>
        <input
          placeholder="Username"
          value={values.username}
          onChange={(e) => setValues({ ...values, username: e.target.value })}
        />

        <input
          placeholder="ID"
          value={values.id}
          onChange={(e) => setValues({ ...values, id: e.target.value })}
        />

        <button type="submit">{editing ? "Salvar" : "Criar"}</button>
        <button type="button" onClick={() => navigate("/users")}>Cancelar</button>
      </form>
    </div>
  );
}