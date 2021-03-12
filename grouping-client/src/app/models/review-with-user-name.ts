import { Review } from "./review";
import { Site } from "./site";

export class ReviewWithUserName extends Review {

  constructor(
    _id: number,
    _groupId: number,
    _site: Site,
    _userId: number,
    _rating: number,
    _comment: string,
    _tocken: string | null,
    private _userName: string
  ) {
    super(_id, _groupId, _site, _userId, _rating, _comment, _tocken);
  }

  public get userName(): string {
    return this._userName;
  }
  public set userName(value: string) {
    this._userName = value;
  }
}
