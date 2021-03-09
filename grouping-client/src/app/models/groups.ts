export class Groups {

  constructor(
    private _id: number,
    private _name: string,
    private _groupAdmin: number,
    private _tocken: string | null
  ) {}

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }
  public get groupAdmin(): number {
    return this._groupAdmin;
  }
  public set groupAdmin(value: number) {
    this._groupAdmin = value;
  }

  public get tocken(): string | null {
    return this._tocken;
  }
  public set tocken(value: string | null) {
    this._tocken = value;
  }
}
