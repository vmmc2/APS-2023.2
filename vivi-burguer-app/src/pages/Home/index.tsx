import { useQueries, useQuery } from "@tanstack/react-query";
import React from "react";
import { Link } from "react-router-dom";
import api from "../../services/api";
import DefaultPage from "../DefaultPage";

function Home() {
  const { data, isLoading } = useQuery<any>({
    queryKey: [`actuator/health`, "GET"],
    retry: false,
  });

  return (
    <DefaultPage>
      <div></div>
    </DefaultPage>
  );
}

export default Home;
