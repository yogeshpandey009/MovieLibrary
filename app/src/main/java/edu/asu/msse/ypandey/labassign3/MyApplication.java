package edu.asu.msse.ypandey.labassign3;

import android.app.Application;

/**
 * Created by yogeshpandey on 09/02/16.
 * Copyright 2016 Yogesh Pandey,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Purpose: SER598 LabAssign3
 * This class is to initialize singleton (MovieLibrary)
 *
 * Ser594 Mobile Systems
 * see http://pooh.poly.asu.edu/Mobile
 * @author Yogesh ypandey@asu.edu
 *         Student, MS Software Engineering, CIDSE, ASU Poly
 * @version January 2016
 *
 */

public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        initSingletons();
    }

    protected void initSingletons()
    {
        // Initialize the instance of MySingleton
        MovieLibrary.initInstance(this.getApplicationContext());
    }

}