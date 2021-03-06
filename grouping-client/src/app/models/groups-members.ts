export class GroupsMembers {

  constructor(
    private _id: number,
    private _userId: number,
    private _groupId: number
  ) {}

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
  public get userId(): number {
    return this._userId;
  }
  public set userId(value: number) {
    this._userId = value;
  }
  public get groupId(): number {
    return this._groupId;
  }
  public set groupId(value: number) {
    this._groupId = value;
  }
}
