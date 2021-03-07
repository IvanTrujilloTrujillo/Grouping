export class Site {

  constructor(
    private _id: number,
    private _name: string,
    private _mapUrl: string
  ) {}

  public get mapUrl(): string {
    return this._mapUrl;
  }
  public set mapUrl(value: string) {
    this._mapUrl = value;
  }
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }
  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
}