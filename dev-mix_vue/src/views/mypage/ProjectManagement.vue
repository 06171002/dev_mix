<template>
  <div class="m-auto w-full">
    <p class="text-lg font-bold">작성한 프로젝트</p>
    <div class="mx-2">
      <div class="flex flex-col gap-4 mb-10 rounded-lg">
        <div class="flex justify-end">
          <button class="border px-2 py-1 rounded-full text-sm hover:bg-gray-200" @click="goProCreate">글 쓰기</button>
        </div>
        <div v-if="boardsarr.length === 0" class="text-center text-gray-500 py-8" style="height: 100px">작성한 글이 없습니다.</div>
        <div class="p-1">
          <!-- 내가 작성한 프로젝트 배열 -->
          <div v-for="(board, index) in boardsarr" :key="index">
            <div class="board-item">
              <div class="flex items-center justify-between w-full">
                <div class="flex gap-3 items-center w-full">
                  <!-- 내용 텍스트, 말줄임표 적용 -->
                  <div class="flex flex-col w-full">
                    <p class="text-sm text-gray-700 mb-1">{{ board.createdAt }}</p>
                    <div class="flex justify-between items-center w-full">
                      <!--지역, 제목, 댓글수, 수정, 삭제 같은라인 배치-->
                      <div class="flex items-center">
                        <div class="bg-gray-200 rounded-full px-2 mr-2 text-sm whitespace-nowrap">{{ board.location }}</div>
                        <RouterLink :to="`/projectview/${board.boardId}`" class="flex gap-2">
                          <p class="text-gray-700 w-full truncate max-w-[500px] whitespace-nowrap overflow-hidden">
                            {{ board.title }}
                          </p>
                        </RouterLink>
                        <p class="text-[#d10000] ml-2">[{{ board.commentCount }}]</p>
                      </div>
                      <!-- 수정 삭제를 제목과 댓글 수 오른쪽 끝에 위치시키기 -->
                      <div class="flex flex-wrap gap-3 text-center justify-center items-center text-sm z-20">
                        <RouterLink to="/projectapplicants">
                          <button class="border rounded-full px-2 py-1 mr-10 whitespace-nowrap hover:bg-[#d10000] hover:text-white hover:border-[#d10000]">지원자 확인</button>
                        </RouterLink>
                        <p class="flex-shrink-0 text-gray-500 cursor-pointer hover:text-gray-800 whitespace-nowrap">수정</p>
                        <p class="flex-shrink-0 text-gray-500 cursor-pointer hover:text-gray-800 whitespace-nowrap" @click="doDelete">삭제</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="my-4">
                <hr class="border-t-1 border-gray-300" />
              </div>
            </div>
          </div>

          <!-- 내가 작성한 프로젝트 끝 -->
        </div>
      </div>
    </div>

    <!-- <div class="top-4 flex items-center justify-between">
        <img v-if="profileImage" :src="profileImage" class="h-8 w-8 rounded-full" />
        <img v-else src="/img/people.png" class="h-8 w-8 rounded-full" />
        <div class="text-gray-800 min-w-20 text-center mr-6">닉네임</div>
      </div>
      <div class="flex gap-2 items-center w-full">
        댓글내용 텍스트, 말줄임표 적용
        <div class="flex w-full">
          <p class="text-gray-700 cursor-pointer text-center px-2">글 제목</p>
          <p class="text-gray-700 min-w-20">[신청 온 포지션]</p>
          <p class="cursor-pointer text-gray-700 w-full truncate max-w-[500px] whitespace-nowrap overflow-hidden">신청 온 내용</p>
          
        </div>
      </div>
      <div class="flex gap-3 text-center justify-end items-center text-sm">
        <p class="text-sm text-gray-600">2024.11.26</p>
         <p class="flex-shrink-0 text-gray-500 cursor-pointer hover:text-gray-800">신청 취소</p>
      </div>
    </div>
    <div>
      <hr class="border-t border-gray-200" />
    </div> -->

    <!-- <div v-if="boardsarr.length === 0" class="text-center text-gray-500 py-8" style="height: 100px;">참여중인 프로젝트가 없습니다.</div> -->
    <p class="my-4 text-lg font-bold mt-10">참여 중인 프로젝트</p>
    <!--내가 참여중인 프로젝트 카드 시작-->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 xl:grid-cols-4 gap-10">
      <div class="cursor-pointer border rounded-2xl p-4 relative project-card">
        <div class="top-4 flex items-center justify-between">
          <div class="border px-2 rounded-full mb-2 bg-gray-200 text-gray-800">지역</div>
        </div>
        <div class="text-sm mb-2 text-gray-800">모집 마감일 |</div>
        <div class="text-xl font-bold mb-2 text-gray-800">제목</div>
        <!--기술 아이콘-->
        <div class="flex pt-2 gap-3 mb-1">
          <div>
            <!-- <img class="w-10 h-10" :src="tech.techStackImageUrl" /> -->
          </div>
        </div>
        <div class="flex flex-col">
          <p class="flex-grow text-right text-sm py-2 text-gray-800">작성자</p>
          <!-- <p class="flex-grow text-right text-sm py-2" v-else>&nbsp;</p> -->
          <div class="flex justify-between items-center mt-3">
            <!-- 인원 정보 (0 / 총 인원) -->
            <div class="relative group">
              <!-- 인원 0 / 총 인원 표시 -->
              <div class="text-sm cursor-pointer text-gray-800">인원 남은 / 총인원</div>
              <!-- 개별 인원 수 출력, 마우스를 올리면 나타나도록 -->
              <div
                class="absolute z-10 bg-white left-14 bottom-2 p-2 shadow-lg rounded-xl mt-2 w-auto whitespace-nowrap opacity-0 group-hover:opacity-100 group-hover:block transition-opacity duration-300"
              >
                <div class="text-sm my-1 m-auto"><span class="font-bold rounded-md px-1 text-gray-800"> 포지션명 -</span>요구인원수명</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--내가 참여중인 프로젝트 카드 끝-->
  </div>
</template>

<script setup>
import { deleteProject, getPositions, getTechstacks } from '@/api/projectApi';
import { userboards } from '@/api/userApi';
import router from '@/router';
import { useUserStore } from '@/store/userStore';
import { ref, watchEffect } from 'vue';

// 포지션 데이터 가져오기
const positionOptions = ref([]);
// 기술/언어 데이터 가져오기
const techOptions = ref([]);

const selectPositions = async () => {
  try {
    const res = await getPositions();
    // console.log('updatePsotions 데이터 확인: ', res);
    if (Array.isArray(res.data.result)) {
      positionOptions.value = res.data.result;
    } else {
      console.error('분야별 모집 인원 배열 저장 에러', res);
    }
  } catch (error) {
    console.error('실패:', error);
  }
};

const selelctTechstacks = async () => {
  try {
    const res = await getTechstacks();
    // console.log('updateTechstacks 데이터 확인: ', res);
    // techOptions.value = res.result;
    if (Array.isArray(res.data.result)) {
      techOptions.value = res.data.result.map((item) => ({
        techStackName: item.techStackName,
        imageUrl: item.imageUrl
      }));
    } else {
      console.error('기술/언어 배열 저장 에러', res);
    }
  } catch (error) {
    console.error('실패:', error);
  }
};

const boardsarr = ref([]);

// user_id 가져오기
const useStore = useUserStore();
// console.log('user_id: ', useStore.userId);

const myboards = async () => {
  try {
    const res = await userboards(useStore.userId);
    // console.log('보드 목록: ', res);

    // 데이터 구조 확인 후, boardsarr에 할당
    if (Array.isArray(res.result)) {
      boardsarr.value = res.result;
    } else {
      console.error('res, data, result 확인해보기: ', res);
    }
  } catch (error) {
    console.error('내가 작성한 게시물 가져오기 에러: ', error);
  }
};

const goProCreate = () => {
  router.push('/projectcreate');
};

// 게시글 삭제
// const doDelete = async () => {
//   const isConfirmed = window.confirm('프로젝트를 삭제하시겠습니까?');
//   if (isConfirmed) {
//     try {
//       const res = await deleteProject(boardid);
//       if (res.status === 200) {
//         alert('프로젝트 구인글이 삭제되었습니다.');
//       } else {
//         alert('에러: ' + res.data);
//       }
//     } catch (error) {
//       console.error('삭제 중 오류 발생:', error);
//       alert('삭제 중 오류가 발생했습니다.');
//     }
//   } else {
//     //아무것도 하지않으므로 빈 상태
//   }
// };

watchEffect(() => {
  selectPositions();
  selelctTechstacks();
  myboards();
});
</script>

<style lang="scss" scoped></style>
