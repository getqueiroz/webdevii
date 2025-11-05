import { createBrowserRouter } from "react-router-dom";
import UsersListPage from "./pages/UsersListPage";
import UserFormPage from "./pages/UserFormPage";

export const router = createBrowserRouter([
  { path: "/", element: <p>Home</p> },
 { path: "/users", element: <UsersListPage /> },
 { path: "/users/new", element: <UserFormPage /> },
 { path: "/users/:id/edit", element: <UserFormPage /> },
 { path: "*", element: <p>Página não encontrada</p> }
]);