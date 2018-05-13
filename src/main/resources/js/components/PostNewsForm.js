import React from "react";

export class PostNewsForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            content: "",
            source: "",
            labels: "",
            originLang: "",
            actualTime: "",
            postTime: ""
        }
    }
    render(){
        return(
            <div>
                <form>
                    <p>正文</p>
                    <textarea onChange={this.handleChange}/>
                </form>
            </div>
        );
    }
}
