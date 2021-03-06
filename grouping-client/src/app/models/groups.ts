export class Groups {

  constructor(
    private _id: number,
    private _groupAdmin: number
  ) {}

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
  public get groupAdmin(): number {
    return this._groupAdmin;
  }
  public set groupAdmin(value: number) {
    this._groupAdmin = value;
  }
}
