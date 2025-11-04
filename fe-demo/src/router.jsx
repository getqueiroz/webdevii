import { createBrowserRouter } from "react-router-dom";

export const router = createBrowserRouter([
    { path: "/", element: <p>Home</p> },
    { path: "*", element: <p>Página não encontrada</p> }
]);