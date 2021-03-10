export class InvitationCode {

  constructor(
    private _code: string,
    private _tocken: string | null
  ) {}

  public get code(): string {
    return this._code;
  }
  public set code(value: string) {
    this._code = value;
  }
  public get tocken(): string | null {
    return this._tocken;
  }
  public set tocken(value: string | null) {
    this._tocken = value;
  }

}
