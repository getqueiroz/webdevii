import { createBrowserRouter } from "react-router-dom";
import UsersListPage from "./pages/UsersListPage";

export const router = createBrowserRouter([
  { path: "/", element: <p>Home</p> },
 { path: "/users", element: <UsersListPage /> },
// { path: "/users/new", element: <UserFormPage /> }, // -->  POST http://localhost:9090/users
// { path: "/users/:id/edit", element: <UserFormPage /> },// -->  PUT http://localhost:9090/users/:id
 { path: "*", element: <p>Página não encontrada</p> }
]);