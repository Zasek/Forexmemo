import React from "react";
import {Modal} from "./PostNewsModal.js"
import {PostNewsForm} from "./PostNewsForm.js"

export class Header extends React.Component{
    constructor(props){
        super(props);
        this.state = {isModalOpen:false};

        this.openModal = this.openModal.bind(this);
        this.closeModal = this.closeModal.bind(this);
    }
    render(){
        return(
            <div>
                <h2>This is a header.</h2>
                <button onClick={this.openModal}>Post News</button>
                <Modal isOpen={this.state.isModalOpen} onClose={this.closeModal}>
                    <PostNewsForm />
                    <p><button onClick={this.closeModal}>Close</button></p>
                </Modal>
            </div>
        );
    }
    openModal(){
        this.setState({isModalOpen:true});
    }
    closeModal(){
        this.setState({isModalOpen:false});
    }
}