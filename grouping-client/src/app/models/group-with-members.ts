import { Groups } from "./groups";

export class GroupWithMembers extends Groups {

  constructor(
    _id: number,
    _name: string,
    _groupAdmin: number,
    _tocken: string | null,
    private _groupMembers: number
  ) {
    super(_id, _name, _groupAdmin, _tocken);
  }

  public get groupMembers(): number {
    return this._groupMembers;
  }
  public set groupMembers(value: number) {
    this._groupMembers = value;
  }

}
