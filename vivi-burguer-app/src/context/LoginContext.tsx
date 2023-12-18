import { ReactNode, createContext, useContext } from "react";
import { useLocalStorage } from "../hooks/useLocalStorage";
import React from "react";

type LoginContextProviderProps = {
  children: ReactNode;
};

type LoginContext = {
  isLogged: () => boolean;
  login: LoginData;
  setLogin: (LoginData) => void;
};

export type LoginData = {
  nome: string;
  email: string;
  cpf: string;
  token: string;
};

const LoginContext = createContext({} as LoginContext);

export function useLogin() {
  return useContext(LoginContext);
}

export function LoginProvider({ children }: LoginContextProviderProps) {
  const [login, setLogin] = useLocalStorage<LoginData>("login", {
    nome: "",
    email: "",
    cpf: "",
    token: "",
  });

  function isLogged() {
    return login.nome !== "" && login.email !== "" && login.token !== "";
  }

  return (
    <LoginContext.Provider value={{ isLogged, login, setLogin }}>
      {children}
    </LoginContext.Provider>
  );
}
