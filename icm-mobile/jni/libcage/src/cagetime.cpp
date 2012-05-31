/*
 * Copyright (c) 2010, Yuuki Takano (ytakanoster@gmail.com).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the writers nor the names of its contributors may be
 *    used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

#include "cagetime.hpp"

#ifdef WIN32
namespace libcage{
        int
        gettimeofday(struct timeval *tv, struct timezone *tz)
        {
                FILETIME ft;
                unsigned __int64 tmpres = 0;
                static int tzflag;
 
                if (tv != NULL) {
                        GetSystemTimeAsFileTime(&ft);
 
                        tmpres |= ft.dwHighDateTime;
                        tmpres <<= 32;
                        tmpres |= ft.dwLowDateTime;
 
                        /*converting file time to unix epoch*/
                        tmpres -= DELTA_EPOCH_IN_MICROSECS; 
                        tmpres /= 10;  /*convert into microseconds*/
                        tv->tv_sec = (long)(tmpres / 1000000UL);
                        tv->tv_usec = (long)(tmpres % 1000000UL);
                }
 
                if (tz != NULL) {
                        if (!tzflag) {
                                _tzset();
                                tzflag++;
                        }
                        tz->tz_minuteswest = _timezone / 60;
                        tz->tz_dsttime = _daylight;
                }
 
                return 0;
        }
}

#else

namespace libcage{
        void
        __dummy_func__()
        {

        }
}

#endif // WIN32
