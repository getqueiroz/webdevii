const BASE = "http://localhost:9090";
const RESOURCE = "/users";

async function http(path, { method = "GET", body } = {}) {
    const resp = await fetch(`${BASE}${path}`, {
        method,
        headers: {
            "Content-Type": "application/json",
        },
        body: body ? JSON.stringify(body) : undefined,
    });
    
    if (!resp.ok) {
        throw new Error(`Erro na requisição: ${resp.status}` )
    }
    if (method === "DELETE") return null;
    return resp.json();
}

export const UsersApi = {
    listUsers: () => http(RESOURCE),
    getUser: (id) => http(`${RESOURCE}/${id}`),
    createUser: (userData) => http(RESOURCE, { method: "POST", body: userData }),
    updateUser: (id, userData) => http(`${RESOURCE}/${id}`, { method: "PUT", body: userData }), // <=== bug aqui
    deleteUser: (id) => http(`${RESOURCE}/${id}`, { method: "DELETE" }),
};