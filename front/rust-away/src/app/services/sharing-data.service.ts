import { EventEmitter, Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class SharingDataService {


    private _handlerLoginEventEmitter = new EventEmitter();

    constructor() {}

    get handlerLoginEventEmitter() {
      return this._handlerLoginEventEmitter;
    }
}
