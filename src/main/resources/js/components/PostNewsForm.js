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
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event){
        this.setState({[event.target.name]:event.target.value});
    }

    handleSubmit(event){
        event.preventDefault();
        fetch('http://localhost:8080/news', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                content: this.state.content,
                source: this.state.source,
                labels: this.state.labels,
                originLang: this.state.originLang,
                actualTime: this.state.actualTime,
                postTime: this.state.postTime
            })
        }).catch((error) => {
            console.error(error);
        });
    }

    render(){
        return(
            <div>
                <form onSubmit={this.handleSubmit}>
                    <p>正文</p>
                    <textarea name="content" onChange={this.handleChange} cols="50"/>
                    <p>消息来源</p>
                    <input type="text" name="source" onChange={this.handleChange}/>
                    <p>标签</p>
                    <input type="text" name="labels" onChange={this.handleChange}/>
                    <p>外语原文</p>
                    <input type="text" name="originLang" onChange={this.handleChange}/>
                    <p>实际时间</p>
                    <input type="text" name="actualTime" onChange={this.handleChange}/>
                    <p>上传时间：</p>
                    <input type="text" name="postTime" onChange={this.handleChange}/>
                    <input type="submit" name="submit" value="提交"/>
                </form>
            </div>
        );
    }

}
