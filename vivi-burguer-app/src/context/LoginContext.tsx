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
  name: string;
  email: string;
};

const LoginContext = createContext({} as LoginContext);

export function useLogin() {
  return useContext(LoginContext);
}

export function LoginProvider({ children }: LoginContextProviderProps) {
  const [login, setLogin] = useLocalStorage<LoginData>("login", {
    name: "",
    email: "",
  });

  function isLogged() {
    return false;
  }

  return (
    <LoginContext.Provider value={{ isLogged, login, setLogin }}>
      {children}
    </LoginContext.Provider>
  );
}
