import { httpRequest } from "./services/api";
import React, { useState } from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import type { AxiosRequestConfig } from "axios";
import type { QueryKey } from "@tanstack/react-query";
import Router from "./router";

function App() {
  const defaultQueryFn = ({
    queryKey,
  }: {
    queryKey: QueryKey | (string | AxiosRequestConfig<any>)[];
  }): void | Promise<any> => {
    if (!queryKey || !queryKey.length) return;

    // @ts-ignore
    return httpRequest(...queryKey);
  };

  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        queryFn: defaultQueryFn,
        refetchOnWindowFocus: false,
      },
    },
  });

  return (
    <QueryClientProvider client={queryClient}>
      <Router />
    </QueryClientProvider>
  );
}

export default App;
