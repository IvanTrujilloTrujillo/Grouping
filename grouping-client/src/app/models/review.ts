import { Site } from "./site";

export class Review {

  constructor(
    private _id: number,
    private _groupId: number,
    private _site: Site,
    private _userId: number,
    private _rating: number,
    private _comment: string,
    private _tocken: string | null
  ) {}

  public get tocken(): string | null {
    return this._tocken;
  }
  public set tocken(value: string | null) {
    this._tocken = value;
  }

  public get comment(): string {
    return this._comment;
  }
  public set comment(value: string) {
    this._comment = value;
  }
  public get rating(): number {
    return this._rating;
  }
  public set rating(value: number) {
    this._rating = value;
  }
  public get userId(): number {
    return this._userId;
  }
  public set userId(value: number) {
    this._userId = value;
  }
  public get site(): Site {
    return this._site;
  }
  public set site(value: Site) {
    this._site = value;
  }
  public get groupId(): number {
    return this._groupId;
  }
  public set groupId(value: number) {
    this._groupId = value;
  }
  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
}
