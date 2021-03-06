export class Review {

  constructor(
    private _id: number,
    private _siteId: number,
    private _userId: number,
    private _rating: number,
    private _comment: string
  ) {}

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
  public get siteId(): number {
    return this._siteId;
  }
  public set siteId(value: number) {
    this._siteId = value;
  }
  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
}
