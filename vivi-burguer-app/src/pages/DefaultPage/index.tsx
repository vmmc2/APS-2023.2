import React from "react";
import NavBar from "../../components/NavBar";

export interface DefaultPageProps {
    children: JSX.Element;
}

function DefaultPage({ children }: DefaultPageProps): JSX.Element {
    return (
        <div className="flex flex-col w-screen gap-y-8">
            <NavBar />
            <div className="flex flex-col w-full px-8">
                {
                    children
                }
            </div>
        </div>
    )
}

export default DefaultPage;