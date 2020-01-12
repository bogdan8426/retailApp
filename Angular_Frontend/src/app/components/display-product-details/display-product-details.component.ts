import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {NavbarService} from "../../services/navbar.service";
import {Product} from "../../classes/product";
import {ProductService} from "../../services/product.service";
import {ShoppingCart} from "../../classes/shoppingCart";
import {User} from "../../classes/user";
import {ShoppingCartService} from "../../services/shopping-cart.service";
import {RatingService} from "../../services/rating.service";
import {Rating} from "../../classes/rating";
import { UserService } from '../../services/user.service';
import {QuestionService} from "../../services/question.service";
import {Question} from "../../classes/question";
import {Answer} from "../../classes/answer";

@Component({
  selector: 'app-display-product-details',
  templateUrl: './display-product-details.component.html',
  styleUrls: ['./display-product-details.component.css']
})
export class DisplayProductDetailsComponent implements OnInit, OnDestroy {

  productId: number;
  product: Product;
  productToPrint: Product;
  quantity:number[]=[];
  i: any;
  shoppingCart: ShoppingCart;
  selectedQuantity: number=0;
  message: string;
  messageError:string;
  commForm:boolean=false;ratingForm: boolean= false;
  stars: number[]=[];starsByProduct:number[]=[];
  rating: Rating;
  ratings: Rating[]=[];
  ratingMessage: string;
  ratingStars:string;
  starRating:string;
  noStarSelectedMsg:boolean=false;
  productOnFavorite: String;
  showQuestionArea:boolean=false;
  questions: Question[];
  question: Question;
  questionMessage: boolean=false;
  showAnswerArea:boolean=false;
  answer: Answer;
  answerMessage: boolean=false;
  questionToAnswer: number;
  messageErrorQuantity: boolean=false;
  

  constructor( private activatedRoute: ActivatedRoute,public nav : NavbarService, public productService : ProductService, public shoppingCartService : ShoppingCartService,public userService: UserService, public ratingService: RatingService,
  public questionService : QuestionService) {
    this.activatedRoute.queryParams.subscribe(data => {
      this.productId= data['productId'], console.log(this.productId)
    });
    this.nav.show();
    this.nav.hideDropdown();
    
  }

  ngOnInit() {
    document.body.classList.add('nbg');
    this.getProductById();
    this.getProductRating();
    this.checkIfFavorite();
    this.getComments();
    this.getQuestionsByProductId();


  }

  ngOnDestroy(){
    document.body.classList.remove('nbg');
  }

  getProductById() {
    this.product = new Product();
    this.product.productId = this.productId;
    this.productService.getProductById(this.product).subscribe(data => {
      this.productToPrint = data;
      console.log(this.productToPrint);
      this.getQuantity();
    });
  }

  getQuantity(){
    for(this.i=1;this.i<=this.productToPrint.quantity;this.i++ ){
      this.quantity[this.i]=this.i;

    }
  }

  addToShoppingCart(){
    this.messageErrorQuantity=false;
    if(this.selectedQuantity!=0)
    {
    this.shoppingCart=new ShoppingCart();
    this.shoppingCart.quantity=Number(this.selectedQuantity);
    this.shoppingCart.product=new Product();
    this.shoppingCart.product.productId=this.productId;
    this.shoppingCart.user=new User();
    this.shoppingCart.user.usersId=this.nav.getUserId().usersId;

    this.shoppingCartService.addToShoppingCart(this.shoppingCart).subscribe(data => {
      this.message=data;
      console.log(this.message);
      this.messageError=this.checkResult(this.message);
      console.log(this.messageError);
    });
  }
  else{
    this.message="You need to select a quantity";
    this.messageErrorQuantity=true;
    this.messageError="undefiend";
  }
  }

  setNewQuantity(quantity: number){
    this.selectedQuantity=quantity;
  }

  checkResult(message:string){
    if(message=="success")
    {
      return "true";
    }
    else return "false";
  }

   addToFavorite()
  {
    this.userService.addToFavorite(this.productId).subscribe(data=>{location.reload(); console.log(data)});
  }

  checkIfFavorite()
  {
    this.userService.checkFavorite(this.productId).subscribe(data=>{this.productOnFavorite=data
    console.log(data)});
  }

  deleteFromFavorite()
  {
    this.userService.deleteFromFavorite(this.productId).subscribe(data => { console.log(data);location.reload(); });
  }

  giveRating(){
    this.ratingForm=!this.ratingForm;
  }


  insertRating(comment: string){
    this.rating=new Rating();
    this.rating.ratingStars=this.starRating;
    this.rating.comment=comment;
    this.rating.product=new Product();
    this.rating.product.productId=this.productId;
    this.rating.user=new User();
    this.rating.user.usersId=this.nav.getUserId().usersId;
    if(this.starRating!=null){
      this.ratingService.insertRating(this.rating).subscribe(data => {this.ratingMessage=data,  this.getProductRating();});
      this.noStarSelectedMsg=false;
    }else{
      this.noStarSelectedMsg=true;
    }
  }

  add(ths,sno){
    for (var i=1;i<=5;i++){
      var c=document.getElementById("star"+i)
      c.className="fa fa-star";
    }
    for (var i=1;i<=sno;i++){
      var c=document.getElementById("star"+i)
      if(c.className=="fa fa-star")
      {
        c.className="fa fa-star checked";
      }
    }
    this.starRating=sno;
  }

  getProductRating(){
    this.product= new Product();
    this.product.productId=this.productId;
    this.ratingService.findRatingValueByProductId(this.product).subscribe(data => {this.ratingStars=data, console.log(this.ratingStars)
      for(let i=0;i<Number(this.ratingStars);i++){
        this.stars[i]=i+1;
      }
      this.getComments();
    })
  }

  getComments(){
    this.product= new Product();
    this.product.productId=this.productId;
    this.ratingService.findRatingByProductId(this.product).subscribe(data => {this.ratings=data, console.log(this.ratings);
    if(this.ratings.length>0){
      this.commForm=true;
    }
    })
  }

  getRatingStars(ratingStars: number){
    for(let i=0;i<Number(ratingStars);i++){
      this.starsByProduct[i]=i+1;
    }
    return true;
  }

  displayQuestionArea(){
    this.showQuestionArea=!this.showQuestionArea;
    this.questionMessage=false;
  }

  getQuestionsByProductId(){
    this.questionService.getQuestionsByProductId(this.productId).subscribe(data => {this.questions = data, console.log(this.questions);});
  }

  insertQuestion(questionMessage: string){
    this.question=new Question();
    this.question.questionMessage=questionMessage;
    this.question.product= new Product();
    this.question.product.productId=this.productId;
    this.question.user=new User();
    this.question.user.usersId=this.nav.getUserId().usersId;
    this.questionService.insertQuestion(this.question).subscribe(data => {this.question=data, console.log(this.question);
      this.showQuestionArea=false;
      if(this.question!=null){
        this.getQuestionsByProductId();
      }else{
        this.questionMessage=true;
      }

    });
  }

  displayAnswerArea(id){
    this.showAnswerArea=!this.showAnswerArea;
    this.questionToAnswer=id;
    this.answerMessage=false;
  }

  insertAnswer(answerMessage, questionId){
    this.answer=new Answer();
    this.answer.answerMessage=answerMessage;
    this.answer.user=new User;
    this.answer.user.usersId=this.nav.getUserId().usersId;
    this.answer.question=new Question();
    this.answer.question.questionId=questionId;
    this.questionService.insertAnswer(this.answer,questionId).subscribe(data => {this.answer=data;
      this.showAnswerArea=false;
      if(this.answer!=null){
        this.getQuestionsByProductId();
      }else{
        this.answerMessage=true;
      }
    });
  }

  deleteQuestion(questionId: number){
    this.questionService.deleteQuestion(questionId).subscribe(data => {this.question=data;
      this.getQuestionsByProductId();
    })
  }

  deleteAnswer(answerId: number){
    this.questionService.deleteAnswer(answerId).subscribe(data => {this.answer=data;
      this.getQuestionsByProductId();
    })
  }
  deleteAnswersByQuestionId(questionId: number){
    this.questionService.deleteAnswersByQuestionId(questionId).subscribe(data => {this.answer=data;
      this.getQuestionsByProductId();
      this.deleteQuestion(questionId);
    })
  }


}
