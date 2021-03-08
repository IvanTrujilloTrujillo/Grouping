export class Tocken {
  
  constructor(
    private _tocken: string
  ) {}

  public get tocken(): string {
    return this._tocken;
  }
  public set tocken(value: string) {
    this._tocken = value;
  }
}
